import React, { useEffect, useRef } from "react";

const SingleInputForm = (props) => {
  // #region Variables
  const inputReference = useRef("inputReference"); // for autofocus input
  // #endregion Variables

  //#region Lifecycle Hooks
  useEffect(() => {
    //onMount
    inputReference.current.focus();
  });
  //#endregion

  return (
    <form className="fancy-form">
      <div className="fancy-input-container">
        <input
          ref={inputReference}
          onChange={(event) => props.onInputChange(event)}
          className="fancy-input"
          placeholder={`${props.placeholderText}`}
        />
        <button type="submit" onClick={(event) => props.onSubmitClick(event)}>
          Go
        </button>
      </div>
    </form>
  );
};

export default SingleInputForm;
