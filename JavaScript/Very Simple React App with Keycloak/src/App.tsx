import { useState } from 'react'
import logo from './logo.svg'
import './App.css'
import Login from './Components/Login'
import { Outlet } from 'react-router-dom'

function App() {

  return (
    <div id="app">
      <header className='flex justify-end pr-2 bg-zinc-800 p-2 text-gray-200'>
        <Login></Login>
      </header>
      <main>
        <Outlet/>
      </main>
      <footer>

      </footer>
    </div>
  )
}

export default App
