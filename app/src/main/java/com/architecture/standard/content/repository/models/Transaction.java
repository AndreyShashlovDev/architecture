package com.architecture.standard.content.repository.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Transaction extends RealmObject {

    public static final class Columns {

        public static final String ID = "id";

    }

    @PrimaryKey
    private int mId;

}
