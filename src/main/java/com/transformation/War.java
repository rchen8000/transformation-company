package com.transformation;

import java.util.List;
import java.util.Optional;

public interface War<T> {
	public Optional<List<Integer>> battle();
	public Optional<List<T>> getATeam();
	public Optional<List<T>> getBTeam();
}
