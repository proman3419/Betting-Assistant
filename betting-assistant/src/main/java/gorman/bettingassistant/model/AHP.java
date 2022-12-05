package gorman.bettingassistant.model;

import org.ejml.simple.SimpleMatrix;

import java.util.*;
import java.util.stream.IntStream;

public class AHP {
    private final Map<String, Alternative> alternatives = new HashMap<>();
    private final Map<Attributes, SimpleMatrix> matrixes = new HashMap<>();
    private final Map<Integer, Alternative> alternativeById = new HashMap<>();

    public void addAlternative(String name, Alternative alternative){
        alternatives.put(name, alternative);
        alternativeById.put(alternativeById.size(), alternative);
    }

    private SimpleMatrix createAttributeMatrix(Attributes attribute){
        SimpleMatrix matrix = SimpleMatrix.identity(alternatives.size());

        IntStream.range(0, alternatives.size()).forEach(i -> {
            IntStream.range(i+1, alternatives.size()).forEach(j -> {
                double val = alternativeById.get(i).getAttribute(attribute) / alternativeById.get(j).getAttribute(attribute);
                matrix.set(i, j, val);
                matrix.set(j, i, 1/val);
            });
        });
        return matrix;
    }

    public void createMatrixes(){
        Arrays.stream(Attributes.values()).parallel()
                .forEach(attribute -> matrixes.put(attribute, createAttributeMatrix(attribute)));
    }

}
