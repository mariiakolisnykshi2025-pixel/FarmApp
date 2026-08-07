package Controller;

import Service.Facade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Collections;
import java.util.Map;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;

class TypeControllerTest {

    private Facade facade;
    private TypeController typeController;

    @BeforeEach
    void setUp() {
        facade = Mockito.mock(Facade.class);
        typeController = new TypeController(facade);
    }

    @Test
    void testGetAll() {
        Mockito.when(facade.getAll("farms")).thenReturn(Collections.emptyList());

        List<?> result = typeController.getAll("farms");

        assertEquals(0, result.size());
        Mockito.verify(facade).getAll("farms");
    }

    @Test
    void testTransfer() {
        String result = typeController.transfer("fields", 1, 2, 100);

        assertEquals("Переведення успішно виконано!", result);
        Mockito.verify(facade).transfer("fields", 1, 2, 100);
    }

    @Test
    void testDelete() {
        String result = typeController.delete("farms", 1);

        assertEquals("Об'єкт видалено успішно!", result);
        Mockito.verify(facade).delete("farms", 1);
    }

    @Test
    void testAdd() {
        Map<String, Object> data = Map.of("name", "New Farm");

        String result = typeController.add("farms", data);

        assertEquals("Об'єкт типу farms успішно додано!", result);
        Mockito.verify(facade).save(eq("farms"), anyMap(), eq(false));
    }
}