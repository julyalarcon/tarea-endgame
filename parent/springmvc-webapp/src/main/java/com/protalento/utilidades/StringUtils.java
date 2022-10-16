package com.protalento.utilidades;

import java.util.Objects;

public final class StringUtils {
	
	public StringUtils() {
	}
	
	public static boolean isEmptyOrNull (String string) {
		return Objects.isNull(string) || string.isEmpty();
	}

}
