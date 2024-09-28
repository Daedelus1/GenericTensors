package net.ethanstewart.tensors;

import com.google.common.collect.ImmutableSet;
import com.google.common.truth.Truth;
import org.junit.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {

    @Test
    public void getTest() {
        record TestCase(Function<MatrixAddress, Long> matrixAddressConverter, MatrixAddress maxAddress) {
        }
        ImmutableSet<TestCase> cases = ImmutableSet.<TestCase>builder().add(new TestCase(a -> 51 * a.getY() + a.getX(), new MatrixAddress(50, 50))).build();
        cases.forEach(testCase -> {
            Matrix<Long> matrix = new Matrix<>(testCase.maxAddress, testCase.matrixAddressConverter);
            System.out.println(matrix);
            new View<>(testCase.maxAddress.getOrigin(), testCase.maxAddress).getAllContainedAddresses().forEach(matrixAddress -> {
                System.out.printf("%s | %s | %s\n", matrixAddress, testCase.matrixAddressConverter.apply(matrixAddress), matrix.get(matrixAddress));
                Truth.assertThat(matrix.get(matrixAddress)).isEqualTo(testCase.matrixAddressConverter.apply(matrixAddress));
            });
        });
    }
}