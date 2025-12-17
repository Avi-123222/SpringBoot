import axios from "axios";

const BASE_URL = `http://localhost:2066/tasty-town/api/v1/auth`

export const login = async (data) => {
  const url = `${BASE_URL}/login`
  const response = await axios.post(url, data)
  return response.data;
}