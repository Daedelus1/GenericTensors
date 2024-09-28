package net.ethanstewart.tensors;

import java.util.function.Function;

public class MutableMatrix<T> extends MutableTensor<T, MatrixAddress> {

    public MutableMatrix(MatrixAddress maxAddress, Iterable<T> dataIterable) {
        super(maxAddress, dataIterable);
    }

    public MutableMatrix(MatrixAddress maxAddress, Function<MatrixAddress, T> addressValueConverter) {
        super(maxAddress, addressValueConverter);
    }

    public MutableMatrix(String matrixString,
                  Function<Character, T> characterConverter,
                  MatrixAddress maxAddress) {
        super(maxAddress, matrixAddress -> characterConverter.apply(matrixString.charAt((int)
                ((maxAddress.getY() + 1) * matrixAddress.getY() + matrixAddress.getX())))
        );
    }
}
