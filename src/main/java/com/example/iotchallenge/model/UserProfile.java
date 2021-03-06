package com.example.iotchallenge.model;

import org.hibernate.validator.constraints.Length;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {
    @Id
    @GeneratedValue
    @ApiModelProperty(value = "Autogenerated unique Id")
    private long id;
    @Column(length = 16)
    @NotEmpty
    @Length(min = 3, max = 16)
    @ApiModelProperty(value = "Name of User", example = "Max")
    private String name;
    @ApiModelProperty(value = "User Description", example = "music lover, outdoor enthusiast, explorer by nature")
    private String description;
    @ApiModelProperty(value = "User AvatarImage as base64 String")
    @Lob
    private byte[] avatarImage;
    @ApiModelProperty(value = "Weblink to personal Page", example = "http://my.personal.page")
    private String link;
}
