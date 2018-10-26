<template>
    
</template>

<script>
    import axios from 'axios'

    export default {
        name : 'Hello',
        data() {
            return {
                response: [],
                errors: []
            }
        },

        callRestService() {
            axios.get(`api/index`)
                .then(response => {
                    this.response = response.data
                })
                .catch(e => {
                    this.errors.push(e)
                })
        },
    }
</script>

<style scoped>

</style>