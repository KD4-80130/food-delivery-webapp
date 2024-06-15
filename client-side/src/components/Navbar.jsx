import React from 'react';
import IconButton from '@mui/material/IconButton';
import SearchIcon from '@mui/icons-material/Search';

const Navbar = () => {
  return (
    <div className="flex items-center">
      <div className="logo font-semibold heading mr-4">
        Swaad Delivery Food
      </div>
      <IconButton>
        <SearchIcon className="text-white" style={{ fontSize: '1.5rem' }} />
      </IconButton>
    </div>
  );
}

export default Navbar;
