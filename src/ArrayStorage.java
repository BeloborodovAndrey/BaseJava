import static java.util.stream.IntStream.range;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        range(0, storage.length).forEach(i -> storage[i] = null);
    }

    void save(Resume r) {
        range(0, storage.length).filter(i -> storage[i] == null).findFirst().ifPresent(i -> storage[i] = r);
    }

    Resume get(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid == uuid) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int siz = size();
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid == uuid) {
                for (int j = i; j <= size(); j++) {
                    storage[j] = storage[j+1];
                }
            }
        }
        if (siz == size()) {
            System.out.println("Resume item with uuid " + uuid +" not found");
            return;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int len = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                break;
            }
            len++;
        }
        Resume[] resumes = new Resume[len];
        System.arraycopy(storage, 0, resumes, 0, len);
        return resumes;
    }

    int size() {
        int siz = 0;
        for (Resume r : storage) {
            if (r == null) {
                break;
            }
            siz++;
        }
        ;
        return siz;
    }
}
