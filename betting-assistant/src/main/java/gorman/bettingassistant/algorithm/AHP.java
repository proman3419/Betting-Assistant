package gorman.bettingassistant.algorithm;

import org.ejml.simple.SimpleMatrix;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class AHP {
    private final List<Alternative> alternatives = new ArrayList<>();
    private final Map<Attributes, SimpleMatrix> matrices = new HashMap<>();
    private final Map<Attributes, SimpleMatrix> prioritizationVectors = new HashMap<>();
    private final Map<Integer, Alternative> alternativeById = new HashMap<>();

    public void addAlternative(Alternative alternative){
        alternatives.add(alternative);
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

    //https://people.revoledu.com/kardi/tutorial/AHP/Priority%20Vector.htm
    private SimpleMatrix createPrioritizationVector(Attributes attribute){
        SimpleMatrix attributeMatrix = matrices.get(attribute);
        int n = attributeMatrix.numRows();

        SimpleMatrix attributeMatrixDividedByColSum = new SimpleMatrix(n,n);
        IntStream.range(0, n).forEach(i -> {
            double colSum = IntStream.range(0, n).mapToDouble(j -> attributeMatrix.get(j, i)).sum();

            IntStream.range(0, n).forEach(j -> {
                attributeMatrixDividedByColSum.set(j, i, attributeMatrix.get(j,i) / colSum);
            });
        });

        SimpleMatrix matrix = new SimpleMatrix(alternatives.size(),1);
        IntStream.range(0, n).forEach(i -> {
            double rowSum = IntStream.range(0, n).mapToDouble(num -> attributeMatrixDividedByColSum.get(i,num)).sum();
            matrix.set(i, 0, rowSum / n);
        });

        return matrix;
    }

    public void createMatrixes(){
        Arrays.stream(Attributes.values())
                .forEach(attribute -> matrices.put(attribute, createAttributeMatrix(attribute)));
    }

    public void createPrioritizationVectors(){
        Arrays.stream(Attributes.values())
                .forEach(attribute -> prioritizationVectors.put(attribute, createPrioritizationVector(attribute)));
    }

    public String getBestAlternative(){
        List<Double> finalResults = getFinalResults();

        AtomicInteger bestId = new AtomicInteger();
        IntStream.range(1, finalResults.size()).forEach(i -> {
            if (finalResults.get(bestId.get()) < finalResults.get(i)){
                bestId.set(i);
            }
        });

        System.out.println("FINAL RESULTS: " + finalResults);
        return alternatives.get(bestId.get()).getName();
    }

    private List<Double> getFinalResults() {
        List<Double> finalResults = new ArrayList<>();

        IntStream.range(0, alternatives.size()).forEach(i -> {
            finalResults.add(prioritizationVectors.keySet().stream().mapToDouble(a -> a.getPriority() * prioritizationVectors.get(a).get(i)).sum());
        });
        return finalResults;
    }

}
