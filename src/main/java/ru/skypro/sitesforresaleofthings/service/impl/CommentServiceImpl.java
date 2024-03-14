package ru.skypro.sitesforresaleofthings.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.sitesforresaleofthings.constant.Role;
import ru.skypro.sitesforresaleofthings.dto.CommentDTO;
import ru.skypro.sitesforresaleofthings.dto.CommentsDTO;
import ru.skypro.sitesforresaleofthings.dto.CreateOrUpdateCommentDTO;
import ru.skypro.sitesforresaleofthings.entity.AdEntity;
import ru.skypro.sitesforresaleofthings.entity.CommentEntity;
import ru.skypro.sitesforresaleofthings.entity.UserEntity;
import ru.skypro.sitesforresaleofthings.exception.NotFoundEntityException;
import ru.skypro.sitesforresaleofthings.exception.ValidationException;
import ru.skypro.sitesforresaleofthings.mapper.CommentMapper;
import ru.skypro.sitesforresaleofthings.repository.AdRepository;
import ru.skypro.sitesforresaleofthings.repository.CommentRepository;
import ru.skypro.sitesforresaleofthings.repository.UserRepository;
import ru.skypro.sitesforresaleofthings.service.CommentService;
import ru.skypro.sitesforresaleofthings.service.ValidationService;

import java.util.ArrayList;
import java.util.List;

/**
 * Реализация логики по работе с комментариями
 */
@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final ValidationService validationService;
    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;

    @Override
    public CommentDTO saveComment(Integer id, CreateOrUpdateCommentDTO dto, String userDetails) {
        if (!validationService.validate(dto)) {
            throw new ValidationException(dto.toString());
        }
        UserEntity user = userRepository.findByUsername(userDetails);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        AdEntity ad = adRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("Сущность не найдена!"));
        CommentEntity entity = commentMapper.mapToEntity(dto, user, ad);
        commentRepository.save(entity);
        return commentMapper.mapToDTO(entity);
    }

    @Override
    public CommentDTO updateComment(Integer adId, Integer commentId, CommentDTO dto, String userDetails) {
        UserEntity authorOrAdmin = userRepository.findByUsername(userDetails);
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundEntityException("Сущность не найдена!"));
        if (comment.getAuthor().getUsername().equals(userDetails)
                || authorOrAdmin.getRole().equals(Role.ADMIN)) {
            comment.setText(dto.getText());
            commentRepository.save(comment);
            CommentDTO commentDTO = commentMapper.mapToDTO(comment);
            return commentDTO;
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    @Transactional
    public boolean deleteComment(Integer adId, Integer commentId, String userDetails) {
        UserEntity authorOrAdmin = userRepository.findByUsername(userDetails);
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundEntityException("Сущность не найдена!"));
        if (comment.getAuthor().getUsername().equals(userDetails)
                || authorOrAdmin.getRole().equals(Role.ADMIN)) {
            commentRepository.deleteById(commentId);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public CommentsDTO getComments(Integer id) {
        List<CommentEntity> commentsEntity = commentRepository.findByAdEntityId(id);
        List<CommentDTO> dto = new ArrayList<>();
        for (CommentEntity comment : commentsEntity) {
            dto.add(commentMapper.mapToDTO(comment));
        }
        return new CommentsDTO(dto.size(), dto);
    }
}