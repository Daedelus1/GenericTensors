package net.ethanstewart.tensors;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.truth.Truth;
import org.junit.Test;

public class ViewTest {

    @Test
    public void indexAddressTest() {
        record TestCase(View<MatrixAddress> view, ImmutableMap<MatrixAddress, Long> addressExpectedValueMap) {
        }

        ImmutableSet<TestCase> cases = ImmutableSet.<TestCase>builder()
                .add(new TestCase(
                        new View<>(new MatrixAddress(0, 0), new MatrixAddress(2, 2)),
                        ImmutableMap.<MatrixAddress, Long>builder()
                                .put(new MatrixAddress(0, 0), 0L)
                                .put(new MatrixAddress(1, 0), 1L)
                                .put(new MatrixAddress(2, 0), 2L)
                                .put(new MatrixAddress(0, 1), 3L)
                                .put(new MatrixAddress(1, 1), 4L)
                                .put(new MatrixAddress(2, 1), 5L)
                                .put(new MatrixAddress(0, 2), 6L)
                                .put(new MatrixAddress(1, 2), 7L)
                                .put(new MatrixAddress(2, 2), 8L)
                                .build()
                ))
                .add(new TestCase(
                        new View<>(new MatrixAddress(0, 0), new MatrixAddress(3, 2)),
                        ImmutableMap.<MatrixAddress, Long>builder()
                                .put(new MatrixAddress(0, 0), 0L)
                                .put(new MatrixAddress(1, 0), 1L)
                                .put(new MatrixAddress(2, 0), 2L)
                                .put(new MatrixAddress(3, 0), 3L)
                                .put(new MatrixAddress(0, 1), 4L)
                                .put(new MatrixAddress(1, 1), 5L)
                                .put(new MatrixAddress(2, 1), 6L)
                                .put(new MatrixAddress(3, 1), 7L)
                                .put(new MatrixAddress(0, 2), 8L)
                                .put(new MatrixAddress(1, 2), 9L)
                                .put(new MatrixAddress(2, 2), 10L)
                                .put(new MatrixAddress(3, 2), 11L)
                                .build()
                ))
                .build();
        cases.forEach(testCase -> {
            testCase.addressExpectedValueMap
                    .forEach((address, expectedValue) -> {
                        System.out.printf("%s | %s | %s\n", address, expectedValue, testCase.view.indexAddress(address));
                        Truth.assertThat(testCase.view.indexAddress(address)).isEqualTo(expectedValue);
                    });
            System.out.println();
        });
    }
}
