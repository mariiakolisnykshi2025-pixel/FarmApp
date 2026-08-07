package Repository;

import Entities.Storage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = {Config.AppConfig.class})
@EntityScan(basePackages = "Entities")
@EnableJpaRepositories(basePackages = "Repository")
class StorageRepositoryTest {

    @Autowired
    private StorageRepository farmRepository;

    @Test
    void testSaveAndFindAll() {
        Storage farm = new Storage.StorageBuilder().set_name("Тестовий Склад").build();
        farmRepository.save(farm);

        List<Storage> allStorages = farmRepository.findAll();

        assertEquals(1, allStorages.size());
        assertEquals("Тестовий Склад", allStorages.get(0).get_name());
    }

    @Test
    void testFindById() {
        Storage farm = farmRepository.save(new Storage.StorageBuilder().set_name("Шукаємо мене").build());

        Optional<Storage> retrieved = farmRepository.findById(farm.get_id());

        assertTrue(retrieved.isPresent());
        assertEquals("Шукаємо мене", retrieved.get().get_name());
    }

    @Test
    void testDelete() {
        Storage farm = farmRepository.save(new Storage.StorageBuilder().set_name("На видалення").build());

        farmRepository.deleteById(farm.get_id());

        assertFalse(farmRepository.existsById(farm.get_id()));
    }
}