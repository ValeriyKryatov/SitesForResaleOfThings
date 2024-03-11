package ru.skypro.sitesforresaleofthings.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.sitesforresaleofthings.dto.AdDTO;
import ru.skypro.sitesforresaleofthings.dto.AdsDTO;
import ru.skypro.sitesforresaleofthings.dto.CreateOrUpdateAdDTO;
import ru.skypro.sitesforresaleofthings.dto.ExtendedAdDTO;
import ru.skypro.sitesforresaleofthings.service.AdService;
import ru.skypro.sitesforresaleofthings.service.ImageService;

import java.security.Principal;

/**
 * Контроллер по работе с объявлениями
 */
@Slf4j
//@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@Tag(name = "Объявления")
public class AdController {

    private final AdService adService;
    private final ImageService imageService;

    @GetMapping
    @Operation(
            summary = "Получение всех объявлений"
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AdsDTO.class)
            )
    )
    public ResponseEntity<AdsDTO> getAllAds() {
        try {
            AdsDTO adsDTO = adService.getAllAds();
            return ResponseEntity.ok(adsDTO);
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(
            summary = "Добавление объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            }
    )
    public ResponseEntity<AdDTO> addAd(@RequestPart CreateOrUpdateAdDTO properties,
                                       @RequestPart(name = "image") MultipartFile image,
                                       Principal principal) {
        try {
            AdDTO adDTO = adService.addAd(properties, image, principal.getName());
            return ResponseEntity.ok(adDTO);
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение информации об объявлении",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ExtendedAdDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    )
            }
    )

    public ResponseEntity<ExtendedAdDTO> getAds(@PathVariable Integer id) {
        try {
            ExtendedAdDTO dto = adService.getFullAdsById(id);
            return ResponseEntity.ok().body(dto);
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "No content"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    )
            }
    )

    public ResponseEntity<?> removeAd(@PathVariable Integer id, Principal principal) {
        try {
            return ResponseEntity.ok().body(adService.deleteAdById(id, principal.getName()));
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PatchMapping("/{id}")
    @Operation(
            summary = "Обновление информации об объявлении",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    )
            }
    )
    public ResponseEntity<AdDTO> updateAds(@PathVariable Integer id,
                                           @RequestBody CreateOrUpdateAdDTO ads, Principal principal) {
        try {
            AdDTO adDTO = adService.updateAdsById(id, ads, principal.getName());
            return ResponseEntity.ok(adDTO);
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/me")
    @Operation(
            summary = "Получение объявлений авторизованного пользователя",
            responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdsDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"
            )
    }
    )
    public ResponseEntity<AdsDTO> getAdsMe(Principal principal) {
        try {
            AdsDTO dto = adService.getAdsMe(principal.getName());
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PatchMapping(value = "/{id}/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(
            summary = "Обновление картинки объявления",
            responses= {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE,
                            schema = @Schema(implementation = String[].class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found"
            )
    }
    )
    public ResponseEntity<?> updateImage(@PathVariable Integer id,
                                         @RequestPart MultipartFile image) {
        try {
            return ResponseEntity.ok().body(adService.updateAdImage(id, image));
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/find_by_title")
    @Operation(
            summary = "Поиск объявлений по заголовку",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            }
    )
    public ResponseEntity<AdsDTO> findByTitleAd(@RequestParam String title) {
        try {
            AdsDTO dto = adService.findByTitleAd(title);
            return ResponseEntity.ok().body(dto);
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/image/{id}", produces = {
            MediaType.IMAGE_PNG_VALUE,
            MediaType.IMAGE_JPEG_VALUE,
            MediaType.APPLICATION_OCTET_STREAM_VALUE
    }
    )
    @Operation(
            summary = "Получить картинку объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found",
                            content = @Content())
            })

    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok(imageService.loadImageFile(id));
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}