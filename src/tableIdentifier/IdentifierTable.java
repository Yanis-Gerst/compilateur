package tableIdentifier;

import java.util.ArrayList;
import java.util.List;

public class IdentifierTable {
    private static final int MAX_IDENTIFIERS = 100;
    private List<IdentifierEntry> table;
    private List<Integer> sortedIndexes;

    public IdentifierTable() {
        table = new ArrayList<>();
        sortedIndexes = new ArrayList<>();
    }

    public int search(String name) {
        // Recherche dichotomique dans la table triée
        int left = 0;
        int right = sortedIndexes.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int comparison = table.get(sortedIndexes.get(mid)).getName().compareTo(name);

            if (comparison == 0) {
                return sortedIndexes.get(mid);
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public int insert(String name, IdentifierType type) {
        if (table.size() >= MAX_IDENTIFIERS) {
            throw new RuntimeException("Table des identificateurs pleine");
        }

        if (search(name) != -1) {
            throw new RuntimeException("Identificateur déjà existant: " + name);
        }

        IdentifierEntry entry = new IdentifierEntry(name, type);
        table.add(entry);
        int newIndex = table.size() - 1;

        // Mettre à jour la table d'index triée
        insertIntoSortedIndexes(newIndex);

        return newIndex;
    }

    private void insertIntoSortedIndexes(int newIndex) {
        String newName = table.get(newIndex).getName();
        int insertPosition = 0;

        // Trouver la position d'insertion
        while (insertPosition < sortedIndexes.size() &&
                table.get(sortedIndexes.get(insertPosition)).getName().compareTo(newName) < 0) {
            insertPosition++;
        }

        sortedIndexes.add(insertPosition, newIndex);
    }

    public void displayTable() {
        System.out.println("\nTable des Identificateurs:");
        System.out.println("-------------------------");
        for (int i = 0; i < table.size(); i++) {
            System.out.println("Index " + i + ": " + table.get(i));
        }

        System.out.println("\nTable d'Index Triée:");
        System.out.println("------------------");
        for (int i = 0; i < sortedIndexes.size(); i++) {
            System.out.println("Position " + i + ": Index " + sortedIndexes.get(i) +
                    " (" + table.get(sortedIndexes.get(i)).getName() + ")");
        }
    }

}