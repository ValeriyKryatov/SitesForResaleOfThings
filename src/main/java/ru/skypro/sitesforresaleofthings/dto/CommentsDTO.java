package ru.skypro.sitesforresaleofthings.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO комментариев
 */

/**
 * Свойства:
 * 1) count - общее количество комментариев,
 * 2) results - список комментариев
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentsDTO {

    private Integer count;
    private List<CommentDTO> results;
}