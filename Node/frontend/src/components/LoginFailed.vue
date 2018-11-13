<template>

<div class="LoginFailed">
  <div v-if="this.showModal" @close="this.showModal = false">
    <h5 class="center-align materialize-red-text" >Mauvais mot de passe</h5>
  </div>
</div>
</template>

<script>
    export default {
        name: "LoginFailed",
        props: {
            showModal: false,
        }
    }
</script>

<style scoped>

</style>