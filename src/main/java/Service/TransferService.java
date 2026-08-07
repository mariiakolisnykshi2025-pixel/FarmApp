package Service;

import Entities.Storage;
import Entities.Farm;
import Entities.Field;
import Repository.FarmRepository;
import Repository.FieldRepository;
import Repository.StorageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferService {

    private final FarmRepository farmRepository;
    private final FieldRepository fieldRepository;
    private final StorageRepository storageRepository;

    public TransferService(FarmRepository farmRepository,
                           FieldRepository fieldRepository,
                           StorageRepository storageRepository) {
        this.farmRepository = farmRepository;
        this.fieldRepository = fieldRepository;
        this.storageRepository = storageRepository;
    }

    @Transactional
    public void transferWorkers(String type, int fromId, int toId, int amount) {
        if (amount > 100) throw new RuntimeException("Забагато людей!");

        switch (type.toLowerCase()) {
            case "farms" -> {
                Farm source = farmRepository.findById(fromId).orElseThrow();
                Farm target = farmRepository.findById(toId).orElseThrow();

                if (source.get_number_of_workers() < amount) throw new RuntimeException("Мало людей!");

                source.set_number_of_workers(source.get_number_of_workers() - amount);
                target.set_number_of_workers(target.get_number_of_workers() + amount);

                farmRepository.save(source);
                farmRepository.save(target);
            }
            case "fields" -> {
                Field source = fieldRepository.findById(fromId).orElseThrow();
                Field target = fieldRepository.findById(toId).orElseThrow();

                if (source.get_number_of_workers() < amount) throw new RuntimeException("Мало людей!");

                source.set_number_of_workers(source.get_number_of_workers() - amount);
                target.set_number_of_workers(target.get_number_of_workers() + amount);

                fieldRepository.save(source);
                fieldRepository.save(target);
            }
            case "storages" -> {

                Storage source = storageRepository.findById(fromId).orElseThrow();
                Storage target = storageRepository.findById(toId).orElseThrow();

                if (source.get_number_of_workers() < amount) throw new RuntimeException("Мало людей!");

                source.set_number_of_workers(source.get_number_of_workers() - amount);
                target.set_number_of_workers(target.get_number_of_workers() + amount);

                storageRepository.save(source);
                storageRepository.save(target);
            }
            default -> throw new IllegalArgumentException("Unknown type");
        }
    }
}