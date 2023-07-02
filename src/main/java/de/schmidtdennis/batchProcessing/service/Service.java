package de.schmidtdennis.batchProcessing.service;


import de.schmidtdennis.batchProcessing.repository.DatabaseMapper;

import java.util.List;

public class Service {

    private final DatabaseMapper databaseMapper;

    public Service(DatabaseMapper databaseMapper){
        this.databaseMapper = databaseMapper;
    }

    public int batchInsert(List<String> names, int batchSize){
        int endIndex = 0;
        int size = names.size();
        int totalInserts = 0;

        for(int i = 0; i < size; i += batchSize){
            endIndex = Math.min(i+batchSize, size);
            List<String> subList = names.subList(i, endIndex);
            totalInserts += databaseMapper.insert(subList);
        }

        return totalInserts;
    }


}
