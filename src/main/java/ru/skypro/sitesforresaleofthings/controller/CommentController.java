package ru.skypro.sitesforresaleofthings.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.sitesforresaleofthings.dto.CommentDTO;
import ru.skypro.sitesforresaleofthings.dto.CommentsDTO;
import ru.skypro.sitesforresaleofthings.dto.CreateOrUpdateCommentDTO;
import ru.skypro.sitesforresaleofthings.service.CommentService;

import java.security.Principal;

/**
 * Контроллер по работе с комментариями
 */

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("ads")
@Tag(name = "Комментарии")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{id}/comments")
    @Operation(
            summary = "Получение комментариев объявления"
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not found"
    )
    public ResponseEntity<CommentsDTO> getComments(@PathVariable("id") Integer id) {
        try {
            CommentsDTO commentDTO = commentService.getComments(id);
            return ResponseEntity.ok(commentDTO);
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PostMapping("/{id}/comments")
    @Operation(
            summary = "Добавление комментария к объявлению"
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not found"
    )
    public ResponseEntity<CommentDTO> addComment(@PathVariable("id") Integer id,
                                                 @RequestBody CreateOrUpdateCommentDTO comments,
                                                 Principal principal) {
        try {
            CommentDTO comment = commentService.saveComment(id, comments, principal.getName());
            return ResponseEntity.ok(comment);
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    @Operation(
            summary = "Удаление комментария"
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "403",
            description = "Forbidden"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not found"
    )
    public ResponseEntity<?> deleteComment(@PathVariable Integer adId,
                                           @PathVariable Integer commentId,
                                           Principal principal) {
        try {
            return ResponseEntity.ok(commentService.deleteComment(adId, commentId, principal.getName()));
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    @Operation(
            summary = "Обновление комментария"
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "403",
            description = "Forbidden"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not found"
    )
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Integer adId,
                                                    @PathVariable Integer commentId,
                                                    @RequestBody CommentDTO comment,
                                                    Principal principal) {
        try {
            CommentDTO commentDTO = commentService.updateComment(adId, commentId, comment, principal.getName());
            return ResponseEntity.ok(commentDTO);
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}