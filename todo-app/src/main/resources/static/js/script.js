function handleToggle(todoId) {
  location.href = `/toggle/${todoId}`
  
}
function handleDelete(todoId) {
  //alert("Deleting todo with ID: " , todoId);
 location.href = `/delete/${todoId}`
}