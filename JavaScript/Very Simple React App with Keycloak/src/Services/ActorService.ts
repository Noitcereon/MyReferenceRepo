import KeycloakService from "./KeycloakService";
import MyJavaApi from "./MyJavaApiService";

const retrieveActorsAsync = async (): Promise<void> => {
  try {
    const token = KeycloakService.getToken();
    const response = await fetch(`${MyJavaApi.baseUrl}/actors`, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });

    if (response.ok) {
      const result = await response.json();
      console.log(result);
    }
  } catch (error) {
    console.error(error);
  }
};

const ActorService = {
  retrieveActors: retrieveActorsAsync,
};
export default ActorService;
