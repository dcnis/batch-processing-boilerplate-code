package de.schmidtdennis.batchProcessing.repository;

import java.util.List;

public interface DatabaseMapper {

    int insert(List<String> names);

}
