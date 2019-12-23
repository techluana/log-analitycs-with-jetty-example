package br.com.amcom.laa.services;

import br.com.amcom.laa.dto.ClusterHealthStatusDTO;

public interface HealthService {

	ClusterHealthStatusDTO checkHealth();
}
