package com.churchmutual.core.constants;

public enum ProducerType {

	BROKER("Broker", 1), DIRECT("Direct", 2);

	public static int getTypeFromName(String name) {
		for (ProducerType producerType : values()) {
			if (producerType._name.equals(name)) {
				return producerType._type;
			}
		}

		return 0;
	}

	private ProducerType(String name, int type) {
		_name = name;
		_type = type;
	}

	private String _name;
	private int _type;

}