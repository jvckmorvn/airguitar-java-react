const BASE_URL = 'http://localhost:8080';

const ENDPOINTS = {
  GET_GUITARS: `${BASE_URL}/guitars`,
  GET_GUITAR: (guitarId: string) => `${BASE_URL}/guitars/${guitarId}`
};

export default ENDPOINTS;
