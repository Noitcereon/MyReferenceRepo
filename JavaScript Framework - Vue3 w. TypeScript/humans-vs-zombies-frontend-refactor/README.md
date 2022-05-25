# Humans vs Zombies Frontend

This project holds the frontend used for the Humans vs Zombies application, which is an application about managing state for one or more games of Humans vs Zombies.

It is made with Vue, TypeScript (and Vite for building).

The template uses Vue 3 `<script setup>` SFCs, check out the [script setup docs](https://v3.vuejs.org/api/sfc-script-setup.html#sfc-script-setup) to learn more.

## Recommended IDE Setup

- [VSCode](https://code.visualstudio.com/) + [Volar](https://marketplace.visualstudio.com/items?itemName=johnsoncodehk.volar) + [Tailwind CSS Intellisense](https://marketplace.visualstudio.com/items?itemName=bradlc.vscode-tailwindcss)

## Installation
To setup development for this project you need to follow these steps:

1. Clone the project
2. Navigate to the folder it was cloned to
3. Run `npm install`
4. Serve the files via `npm run dev` for dev build or `npm run preview` for production build
5. Done!

## Usage
App can be used here: [application on heroku](https://dk-hvz-frontend.herokuapp.com/)

## Development
This frontend is made together with a [custom API](https://dk-hvz-backend.herokuapp.com/).

Endpoints can be seen with [Swagger](https://dk-hvz-backend.herokuapp.com/swagger-ui/index.html).

## Deployment with Heroku

Requires Heroku CLI.

To deploy the application you must follow theses steps:

1. Add a Heroku apps git repo as remote
2. Push to that repo's main branch
3. Done!

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)

## Contributors
- @4ndersom
- @Grimaion
- @kasper_andreasen
- @Noitcereon


## Type Support For `.vue` Imports in TS

Since TypeScript cannot handle type information for `.vue` imports, they are shimmed to be a generic Vue component type by default. In most cases this is fine if you don't really care about component prop types outside of templates. However, if you wish to get actual prop types in `.vue` imports (for example to get props validation when using manual `h(...)` calls), you can enable Volar's `.vue` type support plugin by running `Volar: Switch TS Plugin on/off` from VSCode command palette.
