export interface AccessTokenPayload {
    id: number;
    email: string;
    roles: Role[];
    iat: number;
    exp: number;
  }
  
  export interface Role {
    id: number;
    userId: number;
    roleId: number;
  }