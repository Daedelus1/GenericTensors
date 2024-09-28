package net.ethanstewart.tensors;

import java.util.function.Function;

public class Matrix<T> extends Tensor<T, MatrixAddress> {
    public Matrix(MatrixAddress maxAddress, Iterable<T> dataIterable) {
        super(maxAddress, dataIterable);
    }

    public Matrix(MatrixAddress maxAddress, Function<MatrixAddress, T> addressValueConverter) {
        super(maxAddress, addressValueConverter);
    }

    public Matrix(String matrixString,
                  Function<Character, T> characterConverter,
                  MatrixAddress maxAddress) {
        super(maxAddress, matrixAddress -> characterConverter.apply(matrixString.charAt((int)
                ((maxAddress.getY() + 1) * matrixAddress.getY() + matrixAddress.getX())))
        );
    }

}
