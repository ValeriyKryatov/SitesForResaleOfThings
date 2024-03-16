package ru.skypro.sitesforresaleofthings.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO объявлений
 */

/**
 * Свойства:
 * 1) count - общее количество объявлений,
 * 2) results - список объявлений
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdsDTO {

    private Integer count;
    private List<AdDTO> results;
}