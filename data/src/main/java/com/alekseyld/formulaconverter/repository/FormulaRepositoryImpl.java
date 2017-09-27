package com.alekseyld.formulaconverter.repository;

import com.alekseyld.formulaconverter.database.table.FormulaTable;
import com.alekseyld.formulaconverter.database.table.FormulaTable_Table;
import com.alekseyld.formulaconverter.entity.Formula;
import com.alekseyld.formulaconverter.repository.base.FormulaRepository;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Alekseyld on 04.09.2016.
 */
public class FormulaRepositoryImpl implements FormulaRepository {

    //// TODO: 26.09.2017 реализовать репозиторий flowdb и убрать моковые данные
    @Inject
    FormulaRepositoryImpl(){
    }

    @Override
    public boolean saveFormula(Formula formula) {
        if (formula == null)
            return false;

        new FormulaTable()
                .convertFromDomainFormula(formula)
                .save();

        return true;
    }

    @Override
    public boolean deleteFormula(Formula formula) {
        if (formula == null)
            return false;

        new FormulaTable()
                .convertFromDomainFormula(formula)
                .delete();

        return true;
    }

    @Override
    public Formula getFormula(int id) {
        FormulaTable formula = new Select().from(FormulaTable.class)
                .where(FormulaTable_Table.id.eq(id))
                .querySingle();

        if (formula != null)
            return formula.convertToDomainFormula();
        return null;

//      Mock
//        return new Formula("x_0+v_0*t+a*t^2/2")
//                .setId(id)
//                .setName("Формула координаты тела");
    }

    @Override
    public List<Formula> getAll() {

        List<FormulaTable> tableFormulas = new Select().from(FormulaTable.class).queryList();
        List<Formula> formulas = new ArrayList<>();

        for (FormulaTable formulaTable: tableFormulas){
            formulas.add(
                    formulaTable.convertToDomainFormula()
            );
        }

//      Mock
//        formulas.add(new Formula("x_0+v_0*t+a*t^2/2")
//                .setName("Формула координаты тела x="));
//
//        formulas.add(new Formula("(v^2 - v_0^2)/2")
//                .setName("Формула пути, если t неизвестно S="));
//
//        formulas.add(new Formula("(v - v_0) / t")
//                .setName("Формула ускорения a="));
//
//        formulas.add(new Formula("U/R")
//                .setName("Сиди дома I="));

        return formulas;
    }
}
