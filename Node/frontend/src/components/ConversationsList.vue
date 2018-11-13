<template>
    <div class="ConversationList">

        <ul class="collection" v-for="conv in response">
            <li class="collection-item avatar">
                <img src="images/yuna.jpg" alt="" class="circle">
                <span class="title"><h6>{{ conv.name }}</h6></span>
                <p>
                <div v-if="typeof conv.messages[0] !== 'undefined'">
                    {{ conv.messages[0].body }}
                </div>
                <a href="#" @click="$emit('switch-conversation', conv.name)" class="secondary-content"><i
                        class="Large material-icons">send</i></a>
            </li>
        </ul>
        <a @click="$emit('new-conversation', true)" class="btn-floating btn-large waves-effect waves-light red"><i
                class="material-icons">add</i></a>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        name: "ConversationList",
        props: {
            newConversation: Boolean
        },
        data() {
            return {
                response: [],
                errors: []
            }
        },
        mounted() {
            axios.get(`api/conversation`)
                .then(response => {
                    this.response = response.data
                })
                .catch(error => {
                    this.errors.push(error)
                })
        },
        watch: {
            newConversation() {
                console.log("New conv");
                axios.get(`api/conversation`)
                    .then(response => {
                        this.response = response.data
                    })
                    .catch(error => {
                        this.errors.push(error)
                    })
            }

        },
        updated() {
            axios.get(`api/conversation`)
                .then(response => {
                    this.response = response.data
                })
                .catch(error => {
                    this.errors.push(error)
                })
        }
    }


</script>

<style scoped>

</style>