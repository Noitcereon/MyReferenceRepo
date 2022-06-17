# React App with Keycloak

This app shows a use case of a simple React App using Keycloak. 

The biggest complication with using Keycloak with a framework, such as Vue or React, is it's state management.
Keycloak Adapter manages its own state, by using the KeycloakService and initilising in main it works
(caveat: if the keycloak site is down, the app won't load it's contents, because of the way it is currently set up with init Keycloak)

Aside from this issue it is rather simple, if you follow the [Keycloak JS Adapter documentation](https://wjw465150.gitbooks.io/keycloak-documentation/content/securing_apps/topics/oidc/javascript-adapter.html)

## Installation

1. Copy this repository
2. Run `npm install`
3. Setup Keycloak and API (see `~/Java/REST API/Java/PracticeAPIProjectWithKeycloak` & [Keycloak JS Adapter docs](https://wjw465150.gitbooks.io/keycloak-documentation/content/securing_apps/topics/oidc/javascript-adapter.html))

## Usage

To use this app run it with `npm run dev`, which makes the app available at `localhost:3000`

## Maintainer 
@CA Noitcereon (Thomas "Noit" Andersen)
