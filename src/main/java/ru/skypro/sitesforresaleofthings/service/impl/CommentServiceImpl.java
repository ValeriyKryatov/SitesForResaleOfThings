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
    private final AdRepository adsRepository;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;

    @Override
    public CommentDTO saveComment(Integer pk, CreateOrUpdateCommentDTO dto, String userDetails) {
        if (!validationService.validate(dto)) {
            throw new ValidationException(dto.toString());
        }
        UserEntity user = userRepository.findByUsername(userDetails);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        AdEntity ad = adsRepository.findById(pk)
                .orElseThrow(() -> new NotFoundEntityException("Сущность " + "Комментарий " + "не найдена!"));
        CommentEntity entity = commentMapper.mapToEntity(dto, user, ad);
        commentRepository.save(entity);
        return commentMapper.mapToDTO(entity);
    }

    @Override
    public CommentDTO updateComment(Integer adPk, Integer commentPk, CommentDTO dto, String userDetails) {
        UserEntity authorOrAdmin = userRepository.findByUsername(userDetails);
        CommentEntity comment = commentRepository.findById(commentPk)
                .orElseThrow(() -> new NotFoundEntityException("Сущность " + "Комментарий " + "не найдена!"));
        if (comment.getAuthor().getUsername().equals(userDetails)
                || authorOrAdmin.getRole() == (Role.ADMIN)) {
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
    public boolean deleteComment(Integer adPk, Integer commentPk, String userDetails) {
        UserEntity authorOrAdmin = userRepository.findByUsername(userDetails);
        CommentEntity comment = commentRepository.findById(commentPk)
                .orElseThrow(() -> new NotFoundEntityException("Сущность " + "Комментарий " + "не найдена!"));
        if (comment.getAuthor().getUsername().equals(userDetails)
                || authorOrAdmin.getRole() == (Role.ADMIN)) {
            commentRepository.deleteById(commentPk);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public CommentsDTO getComments(Integer pk) {
        List<CommentEntity> commentsEntity = commentRepository.findByAdPk(pk);
        List<CommentDTO> dto = new ArrayList<>();
        for (CommentEntity comment : commentsEntity) {
            dto.add(commentMapper.mapToDTO(comment));
        }
        return new CommentsDTO(dto.size(), dto);
    }
}