package Repository;

import Entities.Field;
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
class FieldRepositoryTest {

    @Autowired
    private FieldRepository farmRepository;

    @Test
    void testSaveAndFindAll() {
        Field farm = new Field.FieldBuilder().set_name("Тестове поле").build();
        farmRepository.save(farm);

        List<Field> allFields = farmRepository.findAll();

        assertEquals(1, allFields.size());
        assertEquals("Тестове поле", allFields.get(0).get_name());
    }

    @Test
    void testFindById() {
        Field farm = farmRepository.save(new Field.FieldBuilder().set_name("Шукаємо мене").build());

        Optional<Field> retrieved = farmRepository.findById(farm.get_id());

        assertTrue(retrieved.isPresent());
        assertEquals("Шукаємо мене", retrieved.get().get_name());
    }

    @Test
    void testDelete() {
        Field farm = farmRepository.save(new Field.FieldBuilder().set_name("На видалення").build());

        farmRepository.deleteById(farm.get_id());

        assertFalse(farmRepository.existsById(farm.get_id()));
    }
}