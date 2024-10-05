package com.batch.spring_boot_batch.mapper;

import com.batch.spring_boot_batch.model.PersonModel;
import com.batch.spring_boot_batch.utility.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class PersonMapper extends BeanWrapperFieldSetMapper<PersonModel> {

    private final Logger logger = LogManager.getLogger(PersonMapper.class);
    @Override
    public PersonModel mapFieldSet(FieldSet fieldSet){
        logger.info("inside mapFieldSet() in mapper class");
        PersonModel model = new PersonModel();
        Utility utility;
        try {
            if (fieldSet.getFieldCount() != 1){
                String data = null;
                utility = new Utility();
                //data = utility.getValidData(fieldSet.readString(0));if (null != data){model.setFirstName(String.valueOf(utility.getDateFormatwithTimeStamp().parse(data)));}
                data = utility.getValidData(fieldSet.readString(0));if (null != data){model.setId(Long.valueOf(data));}
                data = utility.getValidData(fieldSet.readString(1));if (null != data){model.setFirstName(data);}
                data = utility.getValidData(fieldSet.readString(2));if (null != data){model.setLastName(data);}
                data = utility.getValidData(fieldSet.readString(3));if (null != data){model.setAge(Integer.parseInt(data));}
            }
        }catch (ParseException e){
            e.printStackTrace();
        }

        return model;
    }
}
