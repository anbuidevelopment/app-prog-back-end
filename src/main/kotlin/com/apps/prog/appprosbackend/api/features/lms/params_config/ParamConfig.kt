package com.apps.prog.appprosbackend.api.features.lms.params_config

import jakarta.persistence.*

@Entity(name = "ParamConfig")
@Table(name = "ParamConfig", schema = "cfg")
data class ParamConfig(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ParamConfigId")
    val paramConfigId: Long,

    @Column(name = "TableName")
    val tableName: String,

    @Column(name = "ColumnName")
    val columnName: String,

    @Column(name = "ValueInt")
    val valueInt: Short,

    @Column(name = "ValueText")
    val valueText: String,

    @Column(name = "DescriptionEN")
    val descriptionEN: String,

    @Column(name = "DescriptionVN")
    val descriptionVN: String
)
