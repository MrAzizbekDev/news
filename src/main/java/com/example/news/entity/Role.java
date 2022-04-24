package com.example.news.entity;

import com.example.news.entity.enums.PermissionEnum;
import com.example.news.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Role extends AbsEntity {

    @Column(nullable = false,unique = true)
    private String name;

    @Enumerated(value=EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<PermissionEnum>lavozimlarList;

    @Column(length = 500)
    private String description;
}
