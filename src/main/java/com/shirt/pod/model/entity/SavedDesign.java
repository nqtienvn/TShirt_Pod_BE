package com.shirt.pod.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.util.Map;

@Entity
@Table(name = "saved_designs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavedDesign extends BaseEntity {
    private String name;
    // --- XỬ LÝ JSONB ---
    // Map JSON DB thành Map trong Java (hoặc tạo class POJO riêng như DesignData)
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "design_json_data", columnDefinition = "jsonb")
    private Map<String, Object> designJsonData;

    @Column(name = "preview_image_url")
    private String previewImageUrl;

    @Column(name = "is_template")
    private Boolean isTemplate;
}