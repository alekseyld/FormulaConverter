package com.alekseyld.formulaconverter.database.table;

import com.alekseyld.formulaconverter.database.AppDatabase;
import com.alekseyld.formulaconverter.entity.Formula;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by Alekseyld on 27.09.2017.
 */

@Table(database = AppDatabase.class)
public class FormulaTable extends BaseModel {

    @Column
    @PrimaryKey(autoincrement=true)
    int id;

    @Column
    String name;

    @Column
    String rawFormula;

    public Formula convertToDomainFormula(){
        return new Formula(rawFormula)
                .setId(id)
                .setName(name);
    }

    public FormulaTable convertFromDomainFormula(Formula formula){
        this.id = formula.getId();
        this.name = formula.getName();
        this.rawFormula = formula.getRawFormula();

        return this;
    }

}
