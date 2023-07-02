package de.schmidtdennis.batchProcessing;

import de.schmidtdennis.batchProcessing.repository.DatabaseMapper;
import de.schmidtdennis.batchProcessing.service.Service;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.List;

@MockitoSettings
class ServiceTest {

    @Mock
    private DatabaseMapper databaseMapper;

    @InjectMocks
    private Service testee;

    @Test
    public void shouldInsert3batches(){
        // GIVEN

        List<String> names = List.of("Anna", "Berta", "Theodor", "Christian", "Rudolf");

        Mockito.when(databaseMapper.insert(List.of("Anna", "Berta"))).thenReturn(2);
        Mockito.when(databaseMapper.insert(List.of("Theodor", "Christian"))).thenReturn(2);
        Mockito.when(databaseMapper.insert(List.of("Rudolf"))).thenReturn(1);

        int batchSize = 2;

        // WHEN
        int count = testee.batchInsert(names, batchSize);

        // THEN
        Mockito.verify(databaseMapper, Mockito.times(3)).insert(ArgumentMatchers.anyList());
        Mockito.verify(databaseMapper, Mockito.times(1)).insert(List.of("Anna", "Berta"));
        Mockito.verify(databaseMapper, Mockito.times(1)).insert(List.of("Theodor", "Christian"));
        Mockito.verify(databaseMapper, Mockito.times(1)).insert(List.of("Rudolf"));
        Assertions.assertThat(count).isEqualTo(5);
    }

}