<template>
    <div class="NewConversation">
        <h4 class="center header">Nouveau Message</h4>
        <br>
        <br>
        <div class="input-field">
            <i class="material-icons prefix">face</i>
            <input v-model="name" type="text" class="validate" id="name" required>
            <label class="active" for="name">Name</label>
        </div>
        <div class="input-field">
            <i class="material-icons prefix">mode_edit</i>
            <input v-on:keyup.enter="submit" v-model="message" id="first_name2" type="text" class="validate">
            <label class="active" for="first_name2">Message</label>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        name: "NewConversation",
        data() {
            return {
                response: null,
                errors: null,
                message: null,
                name: null
            }
        },
        methods: {
            submit() {
                if (this.name !== null) {
                    let content = {
                            'name': this.name,
                            "messages": [{
                                "body": this.message,
                                "sender": true,
                            }]
                        }
                    ;
                    this.message = null;
                    axios.post("api/conversation/", content)
                        .then(response => {
                            this.response = response.data
                        })
                        .catch(error => {
                            this.errors = error
                        });

                }
                if (this.errors === null) {
                    this.$emit('new-conversation', false);
                }
            }
        },

    }
</script>

<style scoped>

</style>