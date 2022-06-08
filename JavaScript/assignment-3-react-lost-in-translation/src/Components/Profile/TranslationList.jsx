import React from "react";

export default function TranslationList(props) {
  //#region Variables
  //#endregion

  //#region Hooks
  // #endregion

  //#region Functions
  function generateTranslationListItems() {
    let uniqueKey = 0;
    if (props.translations.length === 0) {
      return <p>No translations to display</p>;
    }
    return props.translations.map((translation) => {
      uniqueKey++;
      return <li key={uniqueKey++}>{translation}</li>;
    });
    //#endregion
  }
  return (
    <>
      <ul>{generateTranslationListItems()}</ul>
    </>
  );
}
