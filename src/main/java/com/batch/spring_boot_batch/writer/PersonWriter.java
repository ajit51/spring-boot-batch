package com.batch.spring_boot_batch.writer;

import com.batch.spring_boot_batch.model.PersonModel;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class PersonWriter implements ItemWriter<PersonModel> {
    @Override
    public void write(Chunk<? extends PersonModel> chunk) throws Exception {
        write(chunk);
    }
}
