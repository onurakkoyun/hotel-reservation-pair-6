package com.tobeto.hotel_reservation_pair_6.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Set;

@RequiredArgsConstructor
public enum Role {
//Admin veya başka roller diğer rollerin permissionlarını da alabilir. örnek :  Permission.MANAGER_CREATE
  USER(Collections.emptySet()),
  GUEST(
		  Set.of(
                  Permission.GUEST_READ,
                  Permission.GUEST_UPDATE,
                  Permission.GUEST_DELETE,
                  Permission.GUEST_CREATE
          )
),
  MANAGER( Set.of(
                  Permission.MANAGER_READ,
                  Permission.MANAGER_UPDATE,
                  Permission.MANAGER_DELETE,
                  Permission.MANAGER_CREATE
          )),
  
  ADMIN(
          Set.of(
                  Permission.ADMIN_READ,
                  Permission.ADMIN_UPDATE,
                  Permission.ADMIN_DELETE,
                  Permission.ADMIN_CREATE,
                  Permission.GUEST_READ,
                  Permission.GUEST_UPDATE,
                  Permission.GUEST_DELETE,
                  Permission.GUEST_CREATE,
                  Permission.MANAGER_READ,
                  Permission.MANAGER_UPDATE,
                  Permission.MANAGER_DELETE,
                  Permission.MANAGER_CREATE
          )
  );

  @Getter
  private final Set<Permission> permissions;

  //TODO: Security eklendikten sonra aktif edilecek
  
 /* public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }*/
}
