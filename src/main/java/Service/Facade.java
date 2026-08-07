package Service;
import Entities.Farm;
import Entities.Field;
import Entities.Storage;
import Entities.Type;
import Repository.FarmRepository;
import Repository.FieldRepository;
import Repository.StorageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Facade {
    private final FarmRepository farmRepository;
    private final FieldRepository fieldRepository;
    private final StorageRepository storageRepository;
    private final TransferService transferService;
    private final ObjectMapper objectMapper;

    public Facade(FarmRepository farmRepository, FieldRepository
            fieldRepository, StorageRepository storageRepository,
                  TransferService transferService, ObjectMapper objectMapper) {
        this.farmRepository = farmRepository;
        this.fieldRepository = fieldRepository;
        this.storageRepository = storageRepository;
        this.transferService = transferService;
        this.objectMapper = objectMapper;
    }

    public void save(String type, Map<String, Object> data, boolean isUpdate) {
        switch (type.toLowerCase()) {
            case "farms" -> farmRepository.save(objectMapper.convertValue(data, Farm.class));
            case "fields" -> fieldRepository.save(objectMapper.convertValue(data, Field.class));
            case "storages" -> storageRepository.save(objectMapper.convertValue(data, Storage.class));
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        }
    }

    public List<? extends Type> getAll(String type) {
        return switch (type.toLowerCase()) {
            case "farms" -> farmRepository.findAll();
            case "fields" -> fieldRepository.findAll();
            case "storages" -> storageRepository.findAll();
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };
    }

    public Type getById(String type, int id) {
        return switch (type.toLowerCase()) {
            case "farms" -> farmRepository.findById(id).orElse(null);
            case "fields" -> fieldRepository.findById(id).orElse(null);
            case "storages" -> storageRepository.findById(id).orElse(null);
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };
    }

    public void delete(String type, int id) {
        switch (type.toLowerCase()) {
            case "farms" -> farmRepository.deleteById(id);
            case "fields" -> fieldRepository.deleteById(id);
            case "storages" -> storageRepository.deleteById(id);
        }
    }

    public void transfer(String type, int fromId, int toId, int amount) {
        transferService.transferWorkers(type, fromId, toId, amount);
    }

    public Map<String, Object> calculateProfit(String type, int id, int days) {
        Type entity = getById(type, id);
        if (entity == null) return Map.of("error", "Not found");

        Map<String, Object> response = new HashMap<>();
        response.put("type", type);
        response.put("name", entity.get_name());
        response.put("net_profit", entity.final_profit(days));
        return response;
    }
}