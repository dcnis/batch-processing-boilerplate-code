# batch-processing-template-code
Batch processing refers to the execution of a series of tasks or operations on a large volume of data in a batch.
Generally, it is preferable to perform small, fast transactions rather than large, long-running transactions.

# Template code
Following example demonstrates batch insertion of a list of `names` into a database table.
```
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
```

# Blog

If you want to read the full blog article, you can find [here](https://schmidtdennis.de/batch-processing-template-code).
