package com.reports.hibernate.model.entity.inheritance.table.single;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("2")
public class SingleTableCat extends SingleTablePet {

    private int livesAmount;

}
