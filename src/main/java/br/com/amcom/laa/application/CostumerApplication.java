package br.com.amcom.laa.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import br.com.amcom.laa.resources.ApiTestResources;

public class CostumerApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();

	public CostumerApplication() {
		singletons.add(new ApiTestResources());
	}

	@Override
	public Set<Class<?>> getClasses() {
		return empty;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
