/**
 * 
 */
package com.nisum.test.security;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthTokenData implements Serializable {
	private static final long serialVersionUID = 8088115583723309849L;

	private String name;
	
	private String email;
	
}
