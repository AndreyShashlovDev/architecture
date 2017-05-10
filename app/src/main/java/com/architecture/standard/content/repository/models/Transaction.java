package com.architecture.standard.content.repository.models;


import com.j256.ormlite.table.DatabaseTable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@DatabaseTable
@EqualsAndHashCode(callSuper = false)
public class Transaction extends DaoModel {
}
