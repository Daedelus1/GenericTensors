package net.ethanstewart.tensors;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.StreamSupport;

public class MutableTensor<T, A extends Addressable<A>> {
    private final List<T> data;
    private final View<A> bounds;

    public MutableTensor(A maxAddress, Iterable<T> dataIterable) {
        this.bounds = new View<>(maxAddress.getOrigin(), maxAddress);
        this.data = StreamSupport.stream(dataIterable.spliterator(), false).toList();
    }

    public MutableTensor(A maxAddress, Function<A, T> addressValueConverter) {
        this.bounds = new View<>(maxAddress.getOrigin(), maxAddress);
        this.data = bounds.getAllContainedAddresses()
                .stream()
                .sorted(Comparator.comparing(bounds::indexAddress))
                .map(addressValueConverter)
                .toList();
    }
    public SparseTensor<T, A> toSparseTensor(T valueOfVoid) {
        ImmutableMap.Builder<A, T> builder = ImmutableMap.builder();
        bounds.getAllContainedAddresses().forEach(address -> {
            T value = data.get((int) bounds.indexAddress(address));
            if (!value.equals(valueOfVoid)) {
                builder.put(address, value);
            }
        });
        return new SparseTensor<>(
                builder.build(),
                bounds,
                valueOfVoid
        );
    }


    public T get(A address) {
        return data.get((int) bounds.indexAddress(address));
    }

    public void set(A address, T value) {
        long i = bounds.indexAddress(address);
        Preconditions.checkArgument(i < bounds.indexAddress(bounds.highBoundIncl()));
        data.set((int) i, value);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MutableTensor<?, ?> &&
                this.bounds.equals(((MutableTensor<?, ?>) obj).bounds) &&
                this.data.equals(((MutableTensor<?, ?>) obj).data);
    }
}